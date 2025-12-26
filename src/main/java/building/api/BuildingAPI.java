package building.api;

import building.dto.BuildingDTO;
import building.dto.BuildingRequestDTO;
import building.entity.BuildingEntity;
import building.entity.DistrictEntity;
import building.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@PropertySource("classpath:application.properties")
public class BuildingAPI {
    @Value("${dev.be}")
    private String data;

    @PersistenceContext
    private EntityManager entityManager;

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

    @PostMapping("/api/building")
    @Transactional
    public List<BuildingDTO> insertBuilding(@RequestBody BuildingRequestDTO requestDTO){
        BuildingEntity building = new BuildingEntity();
        building.setName(requestDTO.getName());
        building.setWard(requestDTO.getWard());
        building.setStreet(requestDTO.getStreet());
        DistrictEntity district = new DistrictEntity();
        district.setDistrictId(requestDTO.getDistrictId());
        building.setDistrict(district);
        entityManager.persist(building);
        System.out.println("ok");
        return null;
    }

    @PutMapping("/api/building")
    @Transactional
    public void updateBuilding(@RequestBody BuildingRequestDTO requestDTO){
        BuildingEntity building = new BuildingEntity();
        building.setId(1);
        building.setName(requestDTO.getName());
        building.setWard(requestDTO.getWard());
        building.setStreet(requestDTO.getStreet());
        DistrictEntity district = new DistrictEntity();
        district.setDistrictId(requestDTO.getDistrictId());
        building.setDistrict(district);
        entityManager.merge(building);
        System.out.println("update success");
    }

    @DeleteMapping("api/building1/{id}")
    @Transactional
    public void deleteBuilding(@PathVariable Long id){
        BuildingEntity building = entityManager.find(BuildingEntity.class, id);
        entityManager.remove(building);
        System.out.println("delete success");
    }
}
