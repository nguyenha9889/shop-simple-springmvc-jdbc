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

   @RequestMapping("/add")
   public String catalogAdd(HttpSession session, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      model.addAttribute("formCatalog", new FormCatalog());
      model.addAttribute("view","catalog_add");
      return "admin/index";
   }
   @PostMapping(value = "/add")
   public String doAdd(HttpSession session, Model model,
                       @ModelAttribute("formCatalog") @Validated FormCatalog formCatalog,
                       BindingResult bindingResult){

      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      catalogValidate.validate(formCatalog, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("view","catalog_add");
         model.addAttribute("formAdd", formCatalog);
         return "admin/index";
      }

      Catalog ca = new Catalog();
      ca.setName(formCatalog.getName());
      ca.setDescription(formCatalog.getDescription());
      catalogService.save(ca);
      return "redirect:/admin/catalog";
   }
   @RequestMapping("/edit/{id}")
   public String catalogEdit(HttpSession session, @PathVariable Long id, Model model){
      if (getSessionUser(session) == null) {
         return "admin/login";
      }

      Catalog cat = catalogService.findById(id);
      FormCatalog formCatalog = new FormCatalog(id, cat.getName(), cat.getDescription(), cat.isStatus());
      model.addAttribute("formCatalog",formCatalog);
      model.addAttribute("view","catalog_edit");
      return "admin/index";
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
         model.addAttribute("view","catalog_edit");
         return "admin/index";
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
   public String doDelete(@PathVariable Long id){
      catalogService.delete(id);
      return "redirect:/admin/catalog";
   }
}
