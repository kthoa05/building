package building.api;

import building.dto.BuildingDTO;
import building.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class BuildingAPI {
    @Autowired
    IBuildingService buildingService;

    @GetMapping("/api/buildings")
    public List<BuildingDTO> getAllBuilding(@RequestParam Map<Object, Object> request, @RequestParam(name = "typeCode", required = false) List<String> typeCode){
        return buildingService.getAllBuilding(request, typeCode);
    }
}
