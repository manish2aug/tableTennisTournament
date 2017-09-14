// package za.co.momentum.tabletennis.resources;
//
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.stream.Collectors;
//
// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import za.co.momentum.tabletennis.models.Customer;
//
// @RestController
// @RequestMapping(value="/api")
// public class WebController {
//
// private Map<Integer, Customer> customers = new HashMap<Integer, Customer>(){
//
// {
// put(1, new Customer(1, "Mary", "Taylor", 24));
// put(2, new Customer(2, "Peter", "Smith", 18));
// put(3, new Customer(3, "Lauren", "Taylor", 31));
// }
// };
//
// @GetMapping(value="/customer", produces=MediaType.APPLICATION_JSON_VALUE)
// public List<Customer> getAll(){
// List<Customer> results = customers.entrySet().stream()
// .map(entry ->entry.getValue())
// .collect(Collectors.toList());
// return results;
// }
// }