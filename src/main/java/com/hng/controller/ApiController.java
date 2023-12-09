package com.hng.controller;

import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class ApiController {
   @Autowired
   private ICatalogService catalogService;

   @GetMapping(value = "/catalog/all", produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public List<Catalog> getList(){
      return catalogService.findAll();
   }

   @GetMapping(value = "/catalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public Catalog findCatalogById(@PathVariable Long id) {
      return catalogService.findById(id);
   }
}
