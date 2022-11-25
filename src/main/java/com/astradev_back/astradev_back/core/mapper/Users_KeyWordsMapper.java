package com.astradev_back.astradev_back.core.mapper;

import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.Users_KeyWordsDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.entity.Users_KeyWords;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class Users_KeyWordsMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Users_KeyWords.class, Users_KeyWordsDto.class)
                .byDefault()
                .register();
    }
}