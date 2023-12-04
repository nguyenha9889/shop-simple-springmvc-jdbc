package com.hng.controller;

import com.hng.dto.request.FormCatalog;
import com.hng.model.Catalog;
import com.hng.model.User;
import com.hng.service.ICatalogService;
import com.hng.validate.FormCatalogValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/admin/catalog")
public class AdminCatalogController {

   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private FormCatalogValidate catalogValidate;

   // check session admin login
   public User getSessionUser(HttpSession session){
      return (User) session.getAttribute("adminLogin");
   }

   @RequestMapping
   public String catalog(HttpSession session,
                         Model model,
                         @RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }
      List<Catalog> list = catalogService.findAll(page, size);
      model.addAttribute("catalogs", list);
      model.addAttribute("current_page",page);
      model.addAttribute("size",size);
      model.addAttribute("total_page",new int[catalogService.getTotalPage(list, size)]);
      model.addAttribute("view","catalog");
      return "admin/pages/catalog/index";
   }


   @RequestMapping("/add")
   public String add(HttpSession session, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      model.addAttribute("formCatalog", new FormCatalog());
      model.addAttribute("view","catalog");
      return "admin/pages/catalog/catalog-add";
   }

   @PostMapping("/add")
   public String doAdd(HttpSession session, Model model,
                       @ModelAttribute("formCatalog") @Validated FormCatalog formCatalog,
                       BindingResult bindingResult){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      catalogValidate.validate(formCatalog, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("view","catalog");
         model.addAttribute("formCatalog", formCatalog);
         return "admin/pages/catalog/catalog-add";
      }

      Catalog ca = new Catalog();
      ca.setName(formCatalog.getName());
      ca.setDescription(formCatalog.getDescription());
      catalogService.save(ca);
      return "redirect:/admin/catalog";
   }

   @RequestMapping("/edit/{id}")
   public String edit(HttpSession session, @PathVariable Long id, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      Catalog cat = catalogService.findById(id);
      FormCatalog formCatalog = new FormCatalog(id, cat.getName(), cat.getDescription(), cat.isStatus());
      model.addAttribute("formCatalog",formCatalog);
      model.addAttribute("view","catalog");
      return "admin/pages/catalog/catalog-edit";
   }

   @PostMapping(value = "/update")
   public String doUpdate(HttpSession session, Model model,
                          @ModelAttribute("formCatalog") @Validated FormCatalog formCatalog,
                          BindingResult bindingResult){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      catalogValidate.validate(formCatalog, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("formCatalog",formCatalog);
         model.addAttribute("view","catalog");
         return "admin/pages/catalog/catalog-edit";
      }

      Catalog ca = new Catalog();
      ca.setId(formCatalog.getId());
      ca.setName(formCatalog.getName());
      ca.setDescription(formCatalog.getDescription());
      ca.setStatus(formCatalog.isStatus());
      catalogService.save(ca);

      return "redirect:/admin/catalog";
   }

   @RequestMapping("/delete/{id}")
   public String doDelete(HttpSession session, @PathVariable Long id){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      catalogService.delete(id);
      return "redirect:/admin/catalog";
   }

   // Search catalog by name
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
         return "redirect:/admin/catalog";
      } else {
         List<Catalog> list = catalogService.getListByName(query, page, size);
         model.addAttribute("catalogs", list);
         model.addAttribute("current_page",page);
         model.addAttribute("size",size);
         model.addAttribute("total_page",new int[catalogService.getTotalPage(list, size)]);
         model.addAttribute("view","catalog");
         return "admin/pages/catalog/index";
      }
   }
}
