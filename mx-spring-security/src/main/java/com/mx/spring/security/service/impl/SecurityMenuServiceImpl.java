package com.mx.spring.security.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.result.MxResult;
import com.mx.spring.dev.result.MxView;
import com.mx.spring.dev.support.page.PageBean;
import com.mx.spring.dev.support.page.Pages;
import com.mx.spring.dev.util.BeanUtil;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import com.mx.spring.jdbc.mybatis.plus.page.PageHelper;
import com.mx.spring.security.SaUtil;
import com.mx.spring.security.base.bean.SecurityMenuBean;
import com.mx.spring.security.base.config.MxSecurityConfig;
import com.mx.spring.security.base.enums.MenuType;
import com.mx.spring.security.base.model.SecurityMenu;
import com.mx.spring.security.base.model.SecurityMenuRole;
import com.mx.spring.security.base.vo.SecurityMenuListVO;
import com.mx.spring.security.base.vo.SecurityMenuNavVO;
import com.mx.spring.security.base.vo.SecurityMenuRoleVO;
import com.mx.spring.security.base.vo.SecurityMenuVO;
import com.mx.spring.security.mapper.ISecurityMenuMapper;
import com.mx.spring.security.mapper.ISecurityMenuRoleMapper;
import com.mx.spring.security.service.ISecurityMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.mx.spring.security.base.code.SecurityCode.SECURITY_MENU_HAS_CHILD_NOT_DELETE;
import static com.mx.spring.security.base.code.SecurityCode.SECURITY_MENU_ROLE_EXISTS;


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
    private SaUtil saUtils;
    @Autowired
    private MxSecurityConfig config;

    @Override
    public MxView<List<SecurityMenuNavVO>> nav() throws MxException {
        if (saUtils.isFounder()) {
            return MxView.ok(iMenuMapper.findAllByType(null, config.getClient()));
        }
        //查询带权限的还有公开的 合并到一起
        List<SecurityMenuNavVO> list = iMenuMapper.findAll(saUtils.loginId(), config.getClient());

        List<SecurityMenuNavVO> publicList = iMenuMapper.findAllByType(MenuType.PUBLIC.value(), config.getClient());
        list.addAll(publicList);
        if (CollUtil.isNotEmpty(list)) {
            list = list.stream().sorted(Comparator.comparing(SecurityMenuNavVO::getSort)).collect(Collectors.toList());
        }
        return MxView.ok(list);
    }

    private List<SecurityMenuListVO> createTree(List<SecurityMenuNavVO> resultSet, List<SecurityMenuNavVO> allCategory) {
        List<SecurityMenuListVO> treeList = new ArrayList<>();
        if (CollUtil.isEmpty(resultSet)) {
            return treeList;
        }
        resultSet.forEach(menuNavVO -> {
            SecurityMenuListVO treeVO = new SecurityMenuListVO();
            treeVO.setText(menuNavVO.getName());
            treeVO.setState("{ \"opened\" : false }");
            List<SecurityMenuNavVO> childrenCategory = allCategory.stream().filter(cs -> menuNavVO.getId().equals(cs.getPid())).collect(Collectors.toList());
            if (CollUtil.isNotEmpty(childrenCategory)) {
                List<SecurityMenuListVO> children = createTree(childrenCategory, allCategory);
                treeVO.setChildren(children);
                treeVO.setIcon("mdi mdi-folder-outline");
            } else {
                treeVO.setIcon("mdi mdi-file-outline");
            }

            treeVO.setAttr(MapUtil.builder().put("data_id", menuNavVO.getId()).build());
            treeVO.setSort(menuNavVO.getSort());
            treeList.add(treeVO);
        });
        return treeList;
    }


    @Override
    public MxView<List<SecurityMenuListVO>> list() throws MxException {
        List<SecurityMenuNavVO> menuNavVOList = iMenuMapper.findAllByType(null, config.getClient());
        List<SecurityMenuListVO> menuListVOList = new ArrayList<>();
        if (CollUtil.isEmpty(menuNavVOList)) {
            return MxView.ok(menuListVOList);
        }
        List<SecurityMenuNavVO> topMenuList = menuNavVOList.stream().filter(menuNavVO -> "0".equals(menuNavVO.getPid())).collect(Collectors.toList());
        menuListVOList = createTree(topMenuList, menuNavVOList);
        if (CollUtil.isNotEmpty(menuListVOList)) {
            menuListVOList = menuListVOList.stream().sorted(Comparator.comparing(SecurityMenuListVO::getSort)).collect(Collectors.toList());
        }
        return MxView.ok(menuListVOList);
    }

    @Override
    public MxResult create(SecurityMenuBean menuBean) throws MxException {
        SecurityMenu menu = BeanUtil.copy(menuBean, SecurityMenu::new, (s, t) ->
                t.bind()
                        .id(IdUtil.fastSimpleUUID())
                        .client(config.getClient()));
        return MxResult.result(iMenuMapper.insert(menu));
    }

    @Override
    public MxResult update(String id, SecurityMenuBean menuBean) throws MxException {
        SecurityMenu menu = iMenuMapper.selectById(id);
        if (menu == null) {
            return MxResult.noData();
        }
        menu = BeanUtil.duplicate(menuBean, menu);
        return MxResult.result(iMenuMapper.updateById(menu));
    }

    @Override
    public MxView<SecurityMenuVO> detail(String id) throws MxException {
        return MxView.ok(BeanUtil.copy(iMenuMapper.selectById(id), SecurityMenuVO::new));
    }

    @Override
    public MxResult delete(String id) throws MxException {
        SecurityMenu menu = iMenuMapper.selectOne(Mp.lqw(SecurityMenu.init()).eq(SecurityMenu::getParentId, id).last("limit 1"));
        if (menu != null) {
            return MxResult.create(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getCode()).msg(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getMsg());
        }
        return MxResult.result(iMenuMapper.deleteById(id));
    }

    @Override
    public MxView<Pages<SecurityMenuRoleVO>> roleList(String menuId, String name, PageBean<SecurityMenuRoleVO> pageBean) throws MxException {
        Page<SecurityMenuRoleVO> resultData = iMenuRoleMapper.findAll(menuId, name, PageHelper.in(pageBean));
        return MxView.list(PageHelper.out(resultData, SecurityMenuRoleVO::new));
    }

    @Override
    public MxResult roleCreate(String menuId, String roleId) throws MxException {
        SecurityMenuRole menuRole = iMenuRoleMapper.selectOne(Mp.lqw(SecurityMenuRole.init())
                .eq(SecurityMenuRole::getMenuId, menuId)
                .eq(SecurityMenuRole::getRoleId, roleId)
                .eq(SecurityMenuRole::getClient, config.getClient()));
        if (menuRole != null) {
            return MxResult.create(SECURITY_MENU_ROLE_EXISTS.getCode()).msg(SECURITY_MENU_ROLE_EXISTS.getMsg());
        }
        menuRole = SecurityMenuRole.builder()
                .id(IdUtil.fastSimpleUUID())
                .menuId(menuId)
                .roleId(roleId)
                .client(config.getClient())
                .createUser(saUtils.loginId())
                .createTime(System.currentTimeMillis())
                .build();
        return MxResult.result(iMenuRoleMapper.insert(menuRole));
    }

    @Override
    public MxResult roleDelete(String[] ids) throws MxException {
        return MxResult.result(iMenuRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}
