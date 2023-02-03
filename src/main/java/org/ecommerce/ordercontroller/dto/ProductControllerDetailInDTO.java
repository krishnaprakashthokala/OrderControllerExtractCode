package org.ecommerce.ordercontroller.dto;

import org.springframework.ui.Model;

public class ProductControllerDetailInDTO {
    private Long line;
    private Model model;
    
    
    public Long getLine() {
        return line;
    }

    public void setLine(Long line) {
        this.line = line;
    }
    
    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
    
}