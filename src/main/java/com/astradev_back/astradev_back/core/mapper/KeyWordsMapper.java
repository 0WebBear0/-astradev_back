package com.astradev_back.astradev_back.core.mapper;

import com.astradev_back.astradev_back.core.model.KeyWordsDto;
import com.astradev_back.astradev_back.core.model.UsersDto;
import com.astradev_back.astradev_back.db.entity.KeyWords;
import com.astradev_back.astradev_back.db.entity.Users;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;

@Component
public class KeyWordsMapper extends ConfigurableMapper {
    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(KeyWords.class, KeyWordsDto.class)
                .byDefault()
                .register();
    }
}
