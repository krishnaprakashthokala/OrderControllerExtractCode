package org.ecommerce.web.frontend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.ecommerce.persistence.models.Product;
import org.ecommerce.persistence.models.ProductLine;
import org.ecommerce.persistence.models.User;
import org.ecommerce.security.CurrentUser;
import org.ecommerce.web.frontend.exceptions.SearchSpecificationNotFoundException;
import org.ecommerce.web.models.search.SearchProduct;
import org.ecommerce.web.services.ProductService;
import org.ecommerce.web.services.RecommenderService;

/**
 * @author sergio
 */
@Controller("FrontendProductController")
@RequestMapping("/products")
@SessionAttributes({ ProductController.SEARCH_PRODUCT })
public class ProductController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);
	public static final String SEARCH_PRODUCT = "searchProduct";
	public static final String BINDING_SEARCH_PRODUCT = "org.springframework.validation.BindingResult."
			+ SEARCH_PRODUCT;
	public static final String PRODUCT_PAGE_RESULTS = "pageResults";
	public static final String PRODUCT_LINE_PATH_VARIABLE = "line";

	@Autowired
	private ProductService productService;

	@Autowired
	private RecommenderService recommenderService;

	@ModelAttribute(SEARCH_PRODUCT)
	protected SearchProduct getSearchProduct() {
		return new SearchProduct();
	}

	@GetMapping("/detail/{line}")
	public String detail(@PathVariable Long line, Model model) {
		model.addAttribute("productLineDetail", productService.getProductLineDetail(line));
		return "frontend/product/detail";
	}

	@GetMapping("/recommendation/{number}")
	public String recommendation(@PathVariable Integer number, @CurrentUser User user, Model model) {
		List<Long> lineIds;

		if (user != null) {
			lineIds = recommenderService.recommendForUser(user.getId(), number);
		} else {
			lineIds = recommenderService.recommendForAnonymousUser(number);
		}
		List<ProductLine> productLines = productService.getProductLinesDetail(lineIds);
		model.addAttribute("products", productLines);
		logger.info(" Product Controller  recommendation: " + model);

		return "frontend/fragments/product/lists::recommendations";
	}

	@GetMapping("/search")
	public String search(@ModelAttribute(SEARCH_PRODUCT) SearchProduct searchProduct,
			@RequestParam(value = "query", required = true) String query, RedirectAttributes model) {
		searchProduct.setQuery(query);
		Page<Product> productPage = productService.search(query);
		model.addFlashAttribute(PRODUCT_PAGE_RESULTS, productPage);
		logger.info(" Product Controller  search: " + model);

		return "redirect:/products/search-result";
	}

	@PostMapping("/search")
	public String search(@ModelAttribute(SEARCH_PRODUCT) @Valid SearchProduct searchProduct,
			BindingResult bindingResult, RedirectAttributes model) {
		String url = "redirect:/products/search-result";
		if (bindingResult.hasErrors()) {
			model.addFlashAttribute(BINDING_SEARCH_PRODUCT, bindingResult);
			return url;
		}
		Page<Product> productPage = productService.search(searchProduct);
		model.addFlashAttribute(PRODUCT_PAGE_RESULTS, productPage);
		logger.info(" post Product Controller  search: " + model);

		return url;
	}

	@GetMapping(value = { "/search-result" })
	public String result(@ModelAttribute(PRODUCT_PAGE_RESULTS) Optional<Page<Product>> pageResults, Model model) {
		if (!pageResults.isPresent())
			throw new SearchSpecificationNotFoundException();
		model.addAttribute("bestsellers", new ArrayList<Product>());
		return "frontend/product/list/search_result";
	}

	@GetMapping(value = { "/search-result/{page}" })
	public String result(@ModelAttribute(SEARCH_PRODUCT) SearchProduct searchProduct, @PathVariable Integer page,
			Model model) {
		Page<Product> productPage = productService.search(searchProduct, page);
		model.addAttribute("bestsellers", new ArrayList<Product>());
		model.addAttribute(PRODUCT_PAGE_RESULTS, productPage);
		return "frontend/product/list/search_result";
	}

	@GetMapping(value = { "/categories/{category}", "/categories/{category}/", "/categories/{category}/{page}" })
	public String result(@ModelAttribute(SEARCH_PRODUCT) SearchProduct searchProduct, @PathVariable String category,
			@PathVariable Optional<Integer> page, Model model) {
		Page<Product> productPage = productService.search(searchProduct, page.isPresent() ? page.get() : 0, category);
		model.addAttribute(PRODUCT_PAGE_RESULTS, productPage);
		logger.info("get  Product Controller  category: " + category);

		return "frontend/product/list/categories";
	}

	@PostMapping(value = { "/categories/{category}", "/categories/{category}/" })
	public String result(@ModelAttribute(SEARCH_PRODUCT) SearchProduct searchProduct, @PathVariable String category,
			Model model) {
		Page<Product> productPage = productService.search(searchProduct, 0, category);
		model.addAttribute(PRODUCT_PAGE_RESULTS, productPage);
		logger.info("post  Product Controller  category: " + category);

		return "frontend/product/list/categories";
	}
}
