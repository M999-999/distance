package com.training.distance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages={"com.training.distance.utils","com.training.distance.service","com.training.distance.controller","com.training.distance.repository","com.training.distance.domain"})

//@SpringBootApplication(scanBasePackages={"com.training.distance.repository","com.training.distance.domain"})
//@SpringBootApplication(scanBasePackages={"com.training.distance.repository","com.training.distance.domain","com.training.distance"})
//@ComponentScan("com.training.distance")
//@EntityScan("com.training.distance.domain")
//@EnableJpaAuditing
//@EnableJpaRepositories("com.training.distance.repository")
//@RestController
//@EnableAutoConfiguration
//@EnableTransactionManagement
public class DistanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistanceApplication.class, args);
    }
//    @GetMapping("/hello")
//    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }
 //@Autowired
//    private DistanceService distanceService;
//    private RouteService routeService;
//
//    @PostMapping("/distance")
//    public ResponseEntity<DistanceDto> create(@RequestBody @Valid DistanceDto distanceDto) {
//        distanceService.create(distanceDto);
//
//        return new ResponseEntity<>(distanceDto, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/route/{source}/{target}")
//    public ResponseEntity<List<RouteDto>> getRoute(
//            @PathVariable String source, @PathVariable String target) {
//        return new ResponseEntity<>(routeService.getRoutes(source, target), HttpStatus.OK);
//    }
}
