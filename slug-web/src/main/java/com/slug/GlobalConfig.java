package com.slug;

import com.slug.core.ConfigHandler;

/**
 * @author zhw
 * @version 0.1  15/9/24
 */
public interface GlobalConfig {

    String CHARACTER_ENCODING = "UTF-8";

    String CONFIG_PROPS = "slug.properties";

    String SQL_PROPS = "slug-sql.properties";

    String PK_NAME = "id";


    String VIEW_PATH = ConfigHandler.getString("com.slug.view.path", "/WEB-INF/");

}
