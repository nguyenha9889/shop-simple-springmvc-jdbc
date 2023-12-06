package com.hng.controller;


import com.hng.dto.request.FormProduct;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import com.hng.validate.FormProductValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/product")
public class ProductController {

   @Autowired
   private IProductService productService;
   @Autowired
   private FormProductValidate productValidate;
   @Autowired
   private ICatalogService catalogService;

   @RequestMapping
   public String product(Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size) {

      List<Product> listTotal = productService.findAll();
      if (page < 0) {
         page = 0;
      }
      if (page > productService.getTotalPage(listTotal, size)) {
         page = productService.getTotalPage(listTotal, size) -1;
      }

      List<Product> listPerPage = productService.findAll(page, size);
      model.addAttribute("list", listTotal);
      model.addAttribute("products", listPerPage);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("totalPage", new int[productService.getTotalPage(listTotal, size)]);
      model.addAttribute("view", "product");
      return "admin/pages/product/index";
   }


   @RequestMapping("/add")
   public String add(Model model,
                     @ModelAttribute("formProduct") FormProduct formProduct) {

      model.addAttribute("catalogs", catalogService.findAll());
      model.addAttribute("view", "product");
      return "admin/pages/product/product-add";
   }

   @PostMapping("/add")
   public String doAdd(Model model,
                       @ModelAttribute("formProduct") @Validated FormProduct formProduct,
                       BindingResult bindingResult) {

      productValidate.validate(formProduct, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("formProduct", formProduct);
         model.addAttribute("catalogs", catalogService.findAll());
         model.addAttribute("view", "product");
         return "admin/pages/product/product-add";
      }

      Product p = productService.create(formProduct);
      productService.save(p);
      return "redirect:/admin/product";
   }

   @RequestMapping("/edit/{id}")
   public String edit(@PathVariable Long id,
                      Model model) {

      Product p = productService.findById(id);
      FormProduct formProduct = new FormProduct(
            p.getId(),
            p.getName(),
            p.getCategoryId(),
            p.getDescription(),
            p.getImagePath(),
            p.getUnitPrice(),
            p.isStatus()
      );

      model.addAttribute("cateId", p.getCategoryId());
      model.addAttribute("formProduct", formProduct);
      model.addAttribute("catalogs", catalogService.findAll());
      model.addAttribute("view", "product");
      return "admin/pages/product/product-edit";
   }

   @PostMapping(value = "/update")
   public String doUpdate(Model model,
                          @ModelAttribute("formProduct") @Validated FormProduct formProduct,
                          BindingResult bindingResult) {

      productValidate.validate(formProduct, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("catalogs", catalogService.findAll());
         model.addAttribute("formProduct", formProduct);
         model.addAttribute("view", "product");
         return "admin/pages/product/product-edit";
      }

      Product p = productService.create(formProduct);
      productService.save(p);

      return "redirect:/admin/product";
   }

   @RequestMapping("/delete/{id}")
   public String doDelete(@PathVariable Long id) {

      productService.delete(id);
      return "redirect:/admin/product";
   }

   // Search product by name
   @PostMapping
   public String search(Model model,
                        @RequestParam(name = "query") String query,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size) {

      if (query.trim().isEmpty()) {
         return "redirect:/admin/product";
      } else {
         List<Product> list = productService.getListByName(query, page, size);
         model.addAttribute("products", list);
         model.addAttribute("currentPage", page);
         model.addAttribute("size", size);
         model.addAttribute("totalPage", new int[productService.getTotalPage(list, size)]);
         model.addAttribute("view", "product");
         return "admin/pages/product/index";
      }
   }
}
