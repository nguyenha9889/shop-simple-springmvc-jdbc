package com.hng.controller.admin;

import com.hng.dto.request.FormCatalog;
import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import com.hng.validate.FormCatalogValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Controller
@RequestMapping("/admin/catalog")
public class CatalogController {

   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private FormCatalogValidate catalogValidate;


   @RequestMapping
   public String catalog(Model model,
                         @RequestParam(name = "page", defaultValue = "1") int page,
                         @RequestParam(name = "size", defaultValue = "5") int size){

      List<Catalog> listTotal = catalogService.findAll();
      List<Catalog> listPerPage = catalogService.findAll(page-1, size);
      model.addAttribute("list", listTotal);
      model.addAttribute("catalogs", listPerPage);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("totalPage", new int[catalogService.getTotalPage(listTotal, size)]);
      model.addAttribute("view", "catalog");
      return "admin/pages/catalog/index";
   }


   @RequestMapping("/add")
   public String add(Model model){

      model.addAttribute("formCatalog", new FormCatalog());
      model.addAttribute("view","catalog");
      return "admin/pages/catalog/catalog-add";
   }

   @PostMapping("/add")
   public String doAdd(Model model,
                       @ModelAttribute("formCatalog") @Validated FormCatalog formCatalog,
                       BindingResult bindingResult){

      catalogValidate.validate(formCatalog, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("formCatalog", formCatalog);
         model.addAttribute("view","catalog");
         return "admin/pages/catalog/catalog-add";
      }

      Catalog ca = catalogService.create(formCatalog);
      catalogService.save(ca);
      return "redirect:/admin/catalog";
   }

   @RequestMapping("/edit/{id}")
   public String edit(@PathVariable Long id, Model model){

      Catalog cat = catalogService.findById(id);
      FormCatalog formCatalog = new FormCatalog(id, cat.getName(), cat.getDescription(), cat.isStatus());
      model.addAttribute("formCatalog",formCatalog);
      model.addAttribute("view","catalog");
      return "admin/pages/catalog/catalog-edit";
   }

   @PostMapping(value = "/update")
   public String doUpdate(Model model,
                          @ModelAttribute("formCatalog") @Validated FormCatalog formCatalog,
                          BindingResult bindingResult){

      catalogValidate.validate(formCatalog, bindingResult);
      if (bindingResult.hasErrors()) {
         model.addAttribute("formCatalog", formCatalog);
         model.addAttribute("view","catalog");
         return "admin/pages/catalog/catalog-edit";
      }

      Catalog ca = catalogService.create(formCatalog);
      catalogService.save(ca);

      return "redirect:/admin/catalog";
   }

   @RequestMapping("/delete/{id}")
   public String doDelete(@PathVariable Long id){

      catalogService.delete(id);
      return "redirect:/admin/catalog";
   }

   // Search catalog by name
   @PostMapping
   public String search(Model model,
                        @RequestParam(name = "query") String query,
                        @RequestParam(name = "page", defaultValue = "1") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size){

      if (query.trim().isEmpty()){
         return "redirect:/admin/catalog";
      } else {
         model.addAttribute("query", query);
         List<Catalog> list = catalogService.getListByName(query, page-1, size);
         model.addAttribute("catalogs", list);
         model.addAttribute("currentPage", page);
         model.addAttribute("size", size);
         model.addAttribute("totalPage", new int[catalogService.getTotalPage(list, size)]);
         model.addAttribute("view", "catalog");
         return "admin/pages/catalog/index";
      }
   }
}
