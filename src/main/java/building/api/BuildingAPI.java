package building.api;

import building.dto.BuildingDTO;
import building.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
    @Value("${dev.be}")
    private String data;

    @Autowired
    IBuildingService buildingService;

    @GetMapping("/api/buildings")
    public List<BuildingDTO> getAllBuilding(@RequestParam Map<Object, Object> request, @RequestParam(name = "typeCode", required = false) List<String> typeCode){
        return buildingService.getAllBuilding(request, typeCode);
    }

    @DeleteMapping("/api/building/{id}")
    private void delete(@PathVariable Integer id){
        System.out.println("data: " + data);
    }
}
