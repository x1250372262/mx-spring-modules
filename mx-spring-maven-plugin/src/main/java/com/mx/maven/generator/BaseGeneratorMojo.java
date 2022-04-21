package com.mx.maven.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.fusesource.jansi.Ansi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * @program: mx-maven-plugin
 * @description:
 * @author: mengxiang
 * @create: 2021-09-02 15:11
 **/
public abstract class BaseGeneratorMojo extends AbstractMojo {

    /**
     * 生成输出信息
     *
     * @param color
     * @param msg
     */
    public void out(Ansi.Color color, String msg) {
        System.out.println(ansi().eraseScreen().fg(color).a(msg).reset());
    }


    /**
     * 创建ftl参数集合
     *
     * @return
     */
    public Map<String, Object> baseProp() {
        Map<String, Object> propMap = new HashMap<>();
        //创建时间
        propMap.put("createTime", new Date());
        return propMap;
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
    }


}
