package com.mx.spring.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.mx.spring.dev.bean.PageBean;
import com.mx.spring.dev.core.M;
import com.mx.spring.dev.core.Pages;
import com.mx.spring.dev.core.R;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.dev.support.mybatisplus.MMP;
import com.mx.spring.dev.util.BeanUtils;
import com.mx.spring.dev.util.ListUtils;
import com.mx.spring.dev.util.MapUtils;
import com.mx.spring.security.SaUtils;
import com.mx.spring.security.bean.MenuBean;
import com.mx.spring.security.enums.MenuType;
import com.mx.spring.security.mapper.IMenuMapper;
import com.mx.spring.security.mapper.IMenuRoleMapper;
import com.mx.spring.security.model.Menu;
import com.mx.spring.security.model.MenuRole;
import com.mx.spring.security.service.IMenuService;
import com.mx.spring.security.vo.MenuListVO;
import com.mx.spring.security.vo.MenuNavVO;
import com.mx.spring.security.vo.MenuRoleVO;
import com.mx.spring.security.vo.MenuVO;
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
 * @Author: 徐建鹏.
 * @create: 2021-09-23 15:44
 * @Description:
 */
@Service
public class MenuServiceImpl implements IMenuService {

    private final Menu bean = Menu.init();
    @Autowired
    private IMenuMapper iMenuMapper;
    @Autowired
    private IMenuRoleMapper iMenuRoleMapper;

    @Override
    public M<List<MenuNavVO>> nav() throws MxException {
        if (SaUtils.isFounder()) {
            return M.ok(BeanUtils.copyList(iMenuMapper.selectList(MMP.lqw(bean).orderByAsc(Menu::getSort)), MenuNavVO::new));
        }
        //查询带权限的还有公开的 合并到一起
        List<MenuNavVO> list = iMenuMapper.findAll(SaUtils.loginId());

        List<MenuNavVO> publicList = BeanUtils.copyList(iMenuMapper.selectList(MMP.lqw(bean).eq(Menu::getType, MenuType.PUBLIC.value()).orderByAsc(Menu::getSort)), MenuNavVO::new);
        list.addAll(publicList);
        if (ListUtils.isNotEmpty(list)) {
            list = list.stream().sorted(Comparator.comparing(MenuNavVO::getSort)).collect(Collectors.toList());
        }
        return M.ok(list);
    }

    private List<MenuListVO> createTree(List<MenuNavVO> resultSet, List<MenuNavVO> allCategory) {
        List<MenuListVO> treeList = new ArrayList<>();
        if (ListUtils.isEmpty(resultSet)) {
            return treeList;
        }
        resultSet.forEach(menuNavVO -> {
            MenuListVO treeVO = new MenuListVO();
            treeVO.setText(menuNavVO.getName());
            treeVO.setState("{ \"opened\" : false }");
            List<MenuNavVO> childrenCategory = allCategory.stream().filter(cs -> menuNavVO.getId().equals(cs.getPid())).collect(Collectors.toList());
            if (ListUtils.isNotEmpty(childrenCategory)) {
                List<MenuListVO> children = createTree(childrenCategory, allCategory);
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
    public M<List<MenuListVO>> list() throws MxException {
        List<MenuNavVO> menuNavVOList = BeanUtils.copyList(iMenuMapper.selectList(MMP.lqw(bean).orderByAsc(Menu::getSort)), MenuNavVO::new);
        List<MenuListVO> menuListVOList = new ArrayList<>();
        if (ListUtils.isEmpty(menuNavVOList)) {
            return M.ok(menuListVOList);
        }
        List<MenuNavVO> topMenuList = menuNavVOList.stream().filter(menuNavVO -> "0".equals(menuNavVO.getPid())).collect(Collectors.toList());
        menuListVOList = createTree(topMenuList, menuNavVOList);
        if (ListUtils.isNotEmpty(menuListVOList)) {
            menuListVOList = menuListVOList.stream().sorted(Comparator.comparing(MenuListVO::getSort)).collect(Collectors.toList());
        }
        return M.ok(menuListVOList);
    }

    @Override
    public R create(MenuBean menuBean) throws MxException {
        Menu menu = BeanUtils.copy(menuBean, Menu::new, (s, t) -> t.bind().id(IdUtil.fastSimpleUUID()));
        return R.result(iMenuMapper.insert(menu));
    }

    @Override
    public R update(String id, MenuBean menuBean) throws MxException {
        Menu menu = iMenuMapper.selectById(id);
        if (menu == null) {
            return R.noData();
        }
        menu = BeanUtils.duplicate(menuBean, menu);
        return R.result(iMenuMapper.updateById(menu));
    }

    @Override
    public M<MenuVO> detail(String id) throws MxException {
        return M.ok(BeanUtils.copy(iMenuMapper.selectById(id), MenuVO::new));
    }

    @Override
    public R delete(String id) throws MxException {
        Menu menu = iMenuMapper.selectOne(MMP.lqw(Menu.init()).eq(Menu::getParentId, id).last("limit 1"));
        if (menu != null) {
            return R.create(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getCode()).msg(SECURITY_MENU_HAS_CHILD_NOT_DELETE.getMsg());
        }
        return R.result(iMenuMapper.deleteById(id));
    }

    @Override
    public M<Pages<MenuRoleVO>> roleList(String menuId, String name, PageBean<MenuRoleVO> pageBean) throws MxException {
        return M.list(iMenuRoleMapper.findAll(menuId, name, pageBean.toPage()));
    }

    @Override
    public R roleCreate(String menuId, String roleId) throws MxException {
        MenuRole menuRole = iMenuRoleMapper.selectOne(MMP.lqw(MenuRole.init()).eq(MenuRole::getMenuId, menuId).eq(MenuRole::getRoleId, roleId));
        if (menuRole != null) {
            return R.create(SECURITY_MENU_ROLE_EXISTS.getCode()).msg(SECURITY_MENU_ROLE_EXISTS.getMsg());
        }
        menuRole = MenuRole.builder().id(IdUtil.fastSimpleUUID()).menuId(menuId).roleId(roleId).createUser(SaUtils.loginId()).createTime(System.currentTimeMillis()).build();
        return R.result(iMenuRoleMapper.insert(menuRole));
    }

    @Override
    public R roleDelete(String[] ids) throws MxException {
        return R.result(iMenuRoleMapper.deleteBatchIds(Arrays.asList(ids)));
    }
}
