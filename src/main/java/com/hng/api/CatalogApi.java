package com.hng.api;

import com.hng.model.Catalog;
import com.hng.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CatalogApi {
   @Autowired
   private ICatalogService catalogService;

   @GetMapping(value = "/catalog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
   public Catalog findById(@PathVariable Long id) {
      return catalogService.findById(id);
   }
}
