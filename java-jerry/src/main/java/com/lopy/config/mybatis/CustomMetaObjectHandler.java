package com.lopy.config.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    /**
     * @description Auto fill for table field which label as "fill = FieldFill.INSERT"
     * @author Dex
     * @date 2023/09/21
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start entity insert fill ...");
        strictInsertFill(metaObject, "createDate", Date.class, new Date());
    }

    /**
     * @description Auto fill for table field which label as "fill = FieldFill.UPDATE"
     * @author Dex
     * @date 2023/09/21
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start entity update fill ...");
        setFieldValByName("modifyDate", new Date(), metaObject);
    }
}
