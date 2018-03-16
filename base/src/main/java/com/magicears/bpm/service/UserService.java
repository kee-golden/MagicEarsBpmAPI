package com.magicears.bpm.service;

import com.magicears.bpm.dao.MenuDao;
import com.magicears.bpm.entity.Menu;
import com.magicears.bpm.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by admin on 2018/3/8.
 */
@Transactional
@Service
public class UserService {

    private final MenuDao menuDao;


    public UserService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }


    /**
     * 通过用户id获取菜单
     *
     * @return
     */
    public List<Menu> findMenuByUser(User user) {
        List<Menu> menus = menuDao.findAllByUserId(user.getId());
        List<Menu> roots = new ArrayList<>();
        Iterator<Menu> it = menus.iterator();
        while (it.hasNext()) {
            Menu menu = it.next();
            if (menu != null && menu.getParent() == null) {
                roots.add(menu);
                it.remove();
            }
        }
        menus.forEach(x -> roots.forEach(root -> {
            if (x.getParent() != null) {
                if (root.getId().equals(x.getParent().getId())) {
                    if (root.getChildren() == null) {
                        root.setChildren(new ArrayList<>());
                    }
                    root.getChildren().add(x);
                }
            }
        }));
        menus.forEach(x -> roots.forEach(root -> {
            if (root.getChildren() != null) {
                root.getChildren().forEach(node -> {
                    if (node.getId().equals(x.getParent().getId())) {
                        if (node.getChildren() == null) {
                            node.setChildren(new ArrayList<>());
                        }
                        node.getChildren().add(x);
                    }
                });
            }
        }));
        return  roots;
    }
}
