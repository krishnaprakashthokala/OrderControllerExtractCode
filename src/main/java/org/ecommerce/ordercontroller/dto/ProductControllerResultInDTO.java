package org.ecommerce.ordercontroller.dto;

import org.ecommerce.web.models.search.SearchProduct;
import java.util.Optional;
import org.springframework.ui.Model;

public class ProductControllerResultInDTO {
    private SearchProduct searchProduct;
    private String category;
    private Optional<Integer> page;
    private Model model;
    
    
    public SearchProduct getSearchProduct() {
        return searchProduct;
    }

    public void setSearchProduct(SearchProduct searchProduct) {
        this.searchProduct = searchProduct;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    public Optional<Integer> getPage() {
        return page;
    }

    public void setPage(Optional<Integer> page) {
        this.page = page;
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
}