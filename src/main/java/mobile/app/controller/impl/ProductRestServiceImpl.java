package mobile.app.controller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBObject;

import mobile.app.config.BaseContext;
import mobile.app.controller.ProductRestService;
import mobile.app.model.Product;

@RestController
@RequestMapping("/product")
public class ProductRestServiceImpl extends BaseContext implements ProductRestService {

	@Override
	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductById(@PathVariable String productId) {
		return productBusiness.getProductDetailsById(productId, getAuthInformation().getName());
	}

	@Override
	@RequestMapping(value = "/like/{productId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject like(@PathVariable String productId) {
		return productBusiness.like(productId, getAuthInformation().getName(), 1);
	}

	@Override
	@RequestMapping(value = "/dislike/{productId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject unlike(@PathVariable String productId) {
		return productBusiness.like(productId, getAuthInformation().getName(), -1);
	}

	@Override
	@RequestMapping(value = "/unlike/{productId}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject removeLike(@PathVariable String productId) {
		return productBusiness.like(productId, getAuthInformation().getName(), 0);
	}

	@Override
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Product saveProduct(@RequestBody Product product) {
		return productBusiness.saveProduct(product, getAuthInformation().getName());
	}

	@Override
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getAllProducts() {
		return productBusiness.getAll();
	}

	@Override
	@RequestMapping(value = "/byName/{name}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductsByName(@PathVariable String name) {
		return productBusiness.getProductsByName(name);
	}

	@Override
	@RequestMapping(value = "/byTags/{searchedText}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductsByTags(@PathVariable String searchedText, @RequestParam(required = false) Integer page) {
		return productBusiness.getProductByTags(searchedText, page);
	}

	@Override
	@RequestMapping(value = "/result-page/{brand}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public DBObject getProductsByNameForResultPage(@RequestParam(required = false) Integer page, @PathVariable String brand) {
		return productBusiness.getProductsByNameForResultPage(page, this.getAuthInformation().getName(), brand);
	}
}
