package building.config;

import building.dto.BuildingDTO;
import building.entity.BuildingEntity;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.typeMap(BuildingEntity.class, BuildingDTO.class)
                .addMappings(mapper -> mapper.skip(BuildingDTO::setRentArea));
        return modelMapper;
    }
}
