package com.favorites.common;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 *
 * @author dw
 * @date 2018/04/04
 */
public class FreemakerUtils {
    private static final Logger logger = LoggerFactory.getLogger(FreemakerUtils.class);

    private FreemakerUtils() {
    }

    public static void makeFieByFtl(String ftlName, List<?> dates) {
        String baseFtl = FreemakerUtils.class.getClassLoader().getResource("ftl/").getPath();
        String baseMakeFile = System.getProperty("user.dir");
        logger.debug("模板目录" + baseFtl);
        logger.debug("生成文件目录" + baseMakeFile);
        File ftlFolder = new File(baseFtl);
        logger.debug(ftlFolder.getAbsolutePath());
        Configuration cf = new Configuration();
        OutputStream os = null;
        OutputStreamWriter ow = null;
        Map<String, Object> root = new HashMap<String, Object>();

        root.put("blogs", dates);
        root.put("nums", dates.size());
        try {
            cf.setDirectoryForTemplateLoading(ftlFolder);
            Template template = cf.getTemplate(ftlName);
            os = new FileOutputStream(new File(baseMakeFile + "/favorites/今日头条收藏.html"));
            ow = new OutputStreamWriter(os);
            template.process(root, ow);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != ow) {
                    ow.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
