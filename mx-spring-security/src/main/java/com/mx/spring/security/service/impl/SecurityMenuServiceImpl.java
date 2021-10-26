package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.support.security.SaUtils;
import com.mx.spring.dev.support.security.model.SecurityMenu;
import com.mx.spring.dev.support.security.model.SecurityMenuRole;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.dev.util.MapUtils;
import com.mx.spring.security.bean.SecurityMenuBean;
import com.mx.spring.security.enums.MenuType;
import com.mx.spring.security.mapper.ISecurityMenuMapper;
import com.mx.spring.security.mapper.ISecurityMenuRoleMapper;
import com.mx.spring.security.service.ISecurityMenuService;
import com.mx.spring.security.vo.SecurityMenuListVO;
import com.mx.spring.security.vo.SecurityMenuNavVO;
import com.mx.spring.security.vo.SecurityMenuRoleVO;
import com.mx.spring.security.vo.SecurityMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.mx.spring.security.code.SecurityCode.SECURITY_MENU_HAS_CHILD_NOT_DELETE;
import static com.mx.spring.security.code.SecurityCode.SECURITY_MENU_ROLE_EXISTS;


/**
 * @Author: mengxiang.
 * @create: 2021-09-23 15:44
 * @Description:
 */
@Service
public class SecurityMenuServiceImpl implements ISecurityMenuService {

    @Autowired
    private ISecurityMenuMapper iMenuMapper;
    @Autowired
    private ISecurityMenuRoleMapper iMenuRoleMapper;
    @Autowired
    private SaUtils saUtils;

    @Override
    public M<List<SecurityMenuNavVO>> nav() throws MxException {
        if (saUtils.isFounder()) {
            return M.ok(iMenuMapper.findAllByType(null));
        }
        //查询带权限的还有公开的 合并到一起
        List<SecurityMenuNavVO> list = iMenuMapper.findAll(saUtils.loginId());

        List<SecurityMenuNavVO> publicList = iMenuMapper.findAllByType(MenuType.PUBLIC.value());
        list.addAll(publicList);
        if (ListUtils.isNotEmpty(list)) {
            list = list.stream().sorted(Comparator.comparing(SecurityMenuNavVO::getSort)).collect(Collectors.toList());
        }
        return M.ok(list);
    }

    private List<SecurityMenuListVO> createTree(List<SecurityMenuNavVO> resultSet, List<SecurityMenuNavVO> allCategory) {
        List<SecurityMenuListVO> treeList = new ArrayList<>();
        if (ListUtils.isEmpty(resultSet)) {
            return treeList;
        }
        resultSet.forEach(menuNavVO -> {
            SecurityMenuListVO treeVO = new SecurityMenuListVO();
            treeVO.setText(menuNavVO.getName());
            treeVO.setState("{ \"opened\" : false }");
            List<SecurityMenuNavVO> childrenCategory = allCategory.stream().filter(cs -> menuNavVO.getId().equals(cs.getPid())).collect(Collectors.toList());
            if (ListUtils.isNotEmpty(childrenCategory)) {
                List<SecurityMenuListVO> children = createTree(childrenCategory, allCategory);
                treeVO.setChildren(children);
                treeVO.setIcon("mdi mdi-folder-outline");
            } else {
                treeVO.setIcon("mdi mdi-file-outline");
            }

            treeVO.setAttr(new MapUtils<>().put("data_id", menuNavVO.getId()).toMap());
            treeVO.setSort(menuNavVO.getSort());
            treeList.add(treeVO);
        });
        return treeList;
    }


    @Override
    public M<List<SecurityMenuListVO>> list() throws MxException {
        List<SecurityMenuNavVO> menuNavVOList = iMenuMapper.findAllByType(null);
        List<SecurityMenuListVO> menuListVOList = new ArrayList<>();
        if (ListUtils.isEmpty(menuNavVOList)) {
            return M.ok(menuListVOList);
        }
        List<SecurityMenuNavVO> topMenuList = menuNavVOList.stream().filter(menuNavVO -> "0".equals(menuNavVO.getPid())).collect(Collectors.toList());
        menuListVOList = createTree(topMenuList, menuNavVOList);
        if (ListUtils.isNotEmpty(menuListVOList)) {
            menuListVOList = menuListVOList.stream().sorted(Comparator.comparing(SecurityMenuListVO::getSort)).collect(Collectors.toList());
        }
        return M.ok(menuListVOList);
    }

    @Override
    public R create(SecurityMenuBean menuBean) throws MxException {
        SecurityMenu menu = BeanUtils.copy(menuBean, SecurityMenu::new, (s, t) -> t.bind().id(IdUtil.fastSimpleUUID()));
        return R.result(iMenuMapper.insert(menu));
    }

    @Override
    public R update(String id, SecurityMenuBean menuBean) throws MxException {
        SecurityMenu menu = iMenuMapper.selectById(id);
        if (menu == null) {
            return R.noData();
        }
        menu = BeanUtils.duplicate(menuBean, menu);
        return R.result(iMenuMapper.updateById(menu));
    }

    @Override
    public M<SecurityMenuVO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(iMenuMapper.selectById(id), SecurityMenuVO::new));
    }

    @Override
    public R delete(String id) throws MxException {
        SecurityMenu menu = iMenuMapper.selectOne(MMP.lqw(SecurityMenu.init()).eq(SecurityMenu::getParentId, id).last("limit 1"));
        if (menu != null) {
            return R.create(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getCode()).msg(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getMsg());
        }
        return R.result(iMenuMapper.deleteById(id));
    }

    @Override
    public M<Pages<SecurityMenuRoleVO>> roleList(String menuId, String name, PageBean<SecurityMenuRoleVO> pageBean) throws MxException {
        return M.list(iMenuRoleMapper.findAll(menuId, name, pageBean.toPage()));
    }

    @Override
    public R roleCreate(String menuId, String roleId) throws MxException {
        SecurityMenuRole menuRole = iMenuRoleMapper.selectOne(MMP.lqw(SecurityMenuRole.init()).eq(SecurityMenuRole::getMenuId, menuId).eq(SecurityMenuRole::getRoleId, roleId));
        if (menuRole != null) {
            return R.create(SECURITY_MENU_ROLE_EXISTS.getCode()).msg(SECURITY_MENU_ROLE_EXISTS.getMsg());
        }
        menuRole = SecurityMenuRole.builder().id(IdUtil.fastSimpleUUID()).menuId(menuId).roleId(roleId).createUser(saUtils.loginId()).createTime(System.currentTimeMillis()).build();
        return R.result(iMenuRoleMapper.insert(menuRole));
    }

    @Override
    public R roleDelete(String[] ids) throws MxException {
        return R.result(iMenuRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}
