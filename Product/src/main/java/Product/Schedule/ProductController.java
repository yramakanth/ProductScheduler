package Product.Schedule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.ProductService;

/**
 * @author YC04292
 * Controller which reads incoming request and responds accordingly
 *
 */
@Component
@Controller
@RequestMapping("/employee")
public class ProductController {
	@Autowired
	public ProductService productService;

	public ProductService getProductService() {
		return productService;
	}

	/**
	 * Method to add product
	 * @param prodID
	 * @param compTime
	 * @return ResponseBody
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public @ResponseBody Product addProduct(@RequestParam("id") String prodID,
			@RequestParam("compTime") int compTime) throws Exception {
		productService.addProds(prodID, compTime);
		Product product = new Product();
		return product;

	}

	/**
	 * Method to fetch total products list
	 * @return ResponseBody
	 */
	@RequestMapping(value = "/fullData", produces = "application/json", method = RequestMethod.GET)
	public @ResponseBody List<Product> fullProductsData() {
		List<Product> products = productService.getProductFullList();
		return products;
	}

	/**
	 * Method to fetch total processed Products list
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/processedProdcuts", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Product>> processedProdList() {
		List<Product> products = productService.getProcessedList();
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
}