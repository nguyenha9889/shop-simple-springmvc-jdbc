package com.hng.controller;


import com.hng.dto.request.FormProduct;
import com.hng.model.Product;
import com.hng.model.User;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import com.hng.validate.FormProductValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

   @Autowired
   private IProductService productService;
   @Autowired
   private FormProductValidate productValidate;
   @Autowired
   private ICatalogService catalogService;

   // check session admin login
   public User getSessionUser(HttpSession session){
      return (User) session.getAttribute("adminLogin");
   }

   @RequestMapping
   public String product(HttpSession session,
                         Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      List<Product> list = productService.findAll(page, size);
      model.addAttribute("products", list);
      model.addAttribute("current_page",page);
      model.addAttribute("size",size);
      model.addAttribute("total_page",new int[productService.getTotalPage(list, size)]);
      model.addAttribute("view","product");
      return "admin/pages/product/index";
   }


   @RequestMapping("/add")
   public String add(HttpSession session, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      model.addAttribute("catalogs", catalogService.findAll());
      model.addAttribute("formProduct", new FormProduct());
      model.addAttribute("view","product");
      return "admin/pages/product/product-add";
   }

   @PostMapping("/add")
   public String doAdd(HttpSession session, Model model,
                       @ModelAttribute("formProduct") @Validated FormProduct formProduct,
                       BindingResult bindingResult){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      productValidate.validate(formProduct, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("view","product");
         model.addAttribute("formProduct", formProduct);
         return "admin/pages/product/product-add";
      }

      Product p = new Product();
      p.setName(formProduct.getName());
      p.setDescription(formProduct.getDescription());
      productService.save(p);
      return "redirect:/admin/product";
   }

   @RequestMapping("/edit/{id}")
   public String edit(HttpSession session, @PathVariable Long id, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      Product p = productService.findById(id);
      FormProduct formProduct = new FormProduct();
      model.addAttribute("formProduct",formProduct);
      model.addAttribute("view","product");
      return "admin/pages/product/product-edit";
   }

   @PostMapping(value = "/update")
   public String doUpdate(HttpSession session, Model model,
                          @ModelAttribute("formProduct") @Validated FormProduct formProduct,
                          BindingResult bindingResult){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      productValidate.validate(formProduct, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("formProduct",formProduct);
         model.addAttribute("view","product");
         return "admin/pages/product/product-edit";
      }

      Product p = new Product();
      p.setId(formProduct.getId());
      p.setName(formProduct.getName());
      p.setDescription(formProduct.getDescription());
      p.setStatus(formProduct.isStatus());
      productService.save(p);

      return "redirect:/admin/product";
   }

   @RequestMapping("/delete/{id}")
   public String doDelete(HttpSession session, @PathVariable Long id){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      productService.delete(id);
      return "redirect:/admin/product";
   }

   // Search product by name
   @PostMapping
   public String search(HttpSession session,
                        Model model,
                        @RequestParam(name = "query") String query,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      if (query.trim().isEmpty()){
         return "redirect:/admin/product";
      } else {
         List<Product> list = productService.getListByName(query, page, size);
         model.addAttribute("products", list);
         model.addAttribute("current_page",page);
         model.addAttribute("size",size);
         model.addAttribute("total_page",new int[productService.getTotalPage(list, size)]);
         model.addAttribute("view","product");
         return "admin/pages/product/index";
      }
   }
}
