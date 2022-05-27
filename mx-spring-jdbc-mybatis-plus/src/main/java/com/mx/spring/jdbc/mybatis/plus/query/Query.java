package com.mx.spring.jdbc.mybatis.plus.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mx.spring.dev.exception.MxException;
import com.mx.spring.jdbc.mybatis.plus.Mp;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: mengxiang.
 * @create: 2022-05-18 13:50
 * @Description:
 */
public class Query<T> {

    public enum OPT {

        /**
         * 等于
         */
        EQ,

        /**
         * 不等于
         */
        NE,

        /**
         * 小于
         */
        LT,

        /**
         * 大于
         */
        GT,

        /**
         * 小于等于
         */
        LE,

        /**
         * 大于等于
         */
        GE,

        /**
         * BETWEEN 值1 AND 值2
         */
        BETWEEN(),

        /**
         * NOT BETWEEN 值1 AND 值2
         */
        NOT_BETWEEN(),

        /**
         * LIKE '%值%'
         */
        LIKE,

        /**
         * NOT LIKE '%值%'
         */
        NOT_LIKE(),

        /**
         * LIKE '%值'
         */
        LIKE_LEFT(),

        /**
         * LIKE '值%'
         */
        LIKE_RIGHT(),

    }

    public static class Param {
        private String filed;
        private Object val1;
        private Object val2;
        private OPT opt;

        private boolean notEmpty;

        public String getFiled() {
            return filed;
        }

        public void setFiled(String filed) {
            this.filed = filed;
        }

        public Object getVal1() {
            return val1;
        }

        public void setVal1(Object val1) {
            this.val1 = val1;
        }

        public Object getVal2() {
            return val2;
        }

        public void setVal2(Object val2) {
            this.val2 = val2;
        }

        public OPT getOpt() {
            return opt;
        }

        public void setOpt(OPT opt) {
            this.opt = opt;
        }

        public boolean isNotEmpty() {
            return notEmpty;
        }

        public void setNotEmpty(boolean notEmpty) {
            this.notEmpty = notEmpty;
        }

        public Param(String filed, Object val1, OPT opt) {
            if (Objects.isNull(filed) || Objects.isNull(opt)) {
                throw new MxException(" filed  opt都不可为空");
            }
            this.filed = filed;
            this.val1 = val1;
            this.opt = opt;
            this.notEmpty = true;
        }

        public Param(String filed, Object val1, OPT opt, boolean notEmpty) {
            if (Objects.isNull(filed) || Objects.isNull(opt)) {
                throw new MxException(" filed  opt都不可为空");
            }
            this.filed = filed;
            this.val1 = val1;
            this.opt = opt;
            this.notEmpty = notEmpty;
        }
    }

    private final T entity;

    private Object condObj;

    private final List<Param> paramList;

    private Query(T entity) {
        paramList = new ArrayList<>();
        this.entity = entity;
    }

  public Query<T> cond(Object condObj){
        this.condObj = condObj;
        return this;
  }

    public static <T> Query<T> create(T entity) {
        return new Query<T>(entity);
    }

    private boolean checkNotEmpty(Object val) {
        boolean flag = true;
        if (Objects.isNull(val)) {
            flag = false;
        } else if (val instanceof String) {
            flag = StringUtils.isNotBlank((String) val);
        } else if (val.getClass().isArray()) {
            flag = ((Object[]) val).length > 0;
        }
        return flag;
    }

    public Query<T> eq(String field, Object val) {
        paramList.add(new Param(field, val, OPT.EQ));
        return this;
    }

    public Query<T> eqEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.EQ, checkNotEmpty(val)));
        return this;
    }

    public Query<T> ne(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NE));
        return this;
    }

    public Query<T> neEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NE, checkNotEmpty(val)));
        return this;
    }

    public Query<T> lt(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LT));
        return this;
    }

    public Query<T> ltEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LT, checkNotEmpty(val)));
        return this;
    }

    public Query<T> gt(String field, Object val) {
        paramList.add(new Param(field, val, OPT.GT));
        return this;
    }

    public Query<T> gtEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.GT, checkNotEmpty(val)));
        return this;
    }

    public Query<T> le(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LE));
        return this;
    }

    public Query<T> leEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LE, checkNotEmpty(val)));
        return this;
    }

    public Query<T> ge(String field, Object val) {
        paramList.add(new Param(field, val, OPT.GE));
        return this;
    }

    public Query<T> geEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.GE, checkNotEmpty(val)));
        return this;
    }

    public Query<T> between(String field, Object val) {
        paramList.add(new Param(field, val, OPT.BETWEEN));
        return this;
    }

    public Query<T> betweenEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.BETWEEN, checkNotEmpty(val)));
        return this;
    }

    public Query<T> notBetween(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NOT_BETWEEN));
        return this;
    }

    public Query<T> notBetweenEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NOT_BETWEEN, checkNotEmpty(val)));
        return this;
    }

    public Query<T> like(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE));
        return this;
    }

    public Query<T> likeEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE, checkNotEmpty(val)));
        return this;
    }

    public Query<T> notLike(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NOT_LIKE));
        return this;
    }

    public Query<T> notLikeEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.NOT_LIKE, checkNotEmpty(val)));
        return this;
    }

    public Query<T> likeLeft(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE_LEFT));
        return this;
    }

    public Query<T> likeLeftEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE_LEFT, checkNotEmpty(val)));
        return this;
    }

    public Query<T> likeRight(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE_RIGHT));
        return this;
    }

    public Query<T> likeRightEx(String field, Object val) {
        paramList.add(new Param(field, val, OPT.LIKE_RIGHT, checkNotEmpty(val)));
        return this;
    }


    public QueryWrapper<T> warpper() {
        QueryWrapper<T> queryWrapper = Mp.qw(entity);
        Warpper.create(queryWrapper, paramList);
        return queryWrapper;
    }

    public QueryWrapper<T> condWarpper() {
        QueryWrapper<T> queryWrapper = Mp.qw(entity);
        Warpper.createCond(queryWrapper, condObj);
        return queryWrapper;
    }

    public QueryWrapper<T> beanWarpper() {
        QueryWrapper<T> queryWrapper = Mp.qw(entity);
        Warpper.createBean(queryWrapper, entity);
        return queryWrapper;
    }
}
