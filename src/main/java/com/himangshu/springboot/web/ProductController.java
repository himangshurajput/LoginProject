package com.himangshu.springboot.web;


import com.himangshu.springboot.model.Product;
import com.himangshu.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/search")
    public String viewSearchPage(Model model){
//        List<Product> productList = productService.listAll(,"fullname","asc") ;
//        model.addAttribute("listProducts",productList);
//        model.addAttribute("keyword",keyword);
        return listByPage(model, 1, "productName", "asc", "");
    }
    @RequestMapping("/search/page/{pageNum}")
    public String listByPage(Model model,
                             @PathVariable(name = "pageNum") int pageNum,
                             @Param("sortField") String sortField,
                             @Param("sortDir") String sortDir,
                             @Param("keyword") String keyword) {
        if(sortDir == null){
            sortDir = "asc";
        }
        if (sortField == null) {
            sortField = "id";
        }
        Page<Product> page = productService.listAll(pageNum, sortField, sortDir,keyword);

        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("Products", listProducts);
        return "search";
    }

    @RequestMapping("/new")
    public String showNewProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newproduct";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("editproduct");
        Product product = productService.getProductById(id);
        mav.addObject("product", product);
        return mav;
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.saveProduct(product);

        return "redirect:/search";
    }

    @RequestMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") int id) {
        productService.deleteProductById(id);
        return "redirect:/search";
    }
}
