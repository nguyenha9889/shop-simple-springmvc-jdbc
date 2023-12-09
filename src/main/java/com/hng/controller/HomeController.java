package com.hng.controller;

import com.hng.dto.request.ProductFilter;
import com.hng.model.Catalog;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping({"/",""})
public class HomeController {

   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private IProductService productService;
   @RequestMapping
   public String home(Model model){

      return "index";
   }

   @RequestMapping("/menu")
   public String menu(Model model,
                      @RequestParam(name = "id", defaultValue = "1") long cateId,
                      @RequestParam(name = "sort", defaultValue = "") String sortByPrice,
                      @RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "size", defaultValue = "5") int size){

      // Chỉ lấy category có sản phẩm
      List<Catalog> catalogs = catalogService.getListHaveProduct();
      if (cateId == 1) {
         cateId = catalogs.get(0).getId();
      }

      ProductFilter productFilter = new ProductFilter();
      productFilter.setCategoryId(cateId); // Lọc theo cateId
      productFilter.setSortByPrice(sortByPrice); // Sắp xếp theo giá

      List<Product> listProPaging = productService.getListByCateIdPaging(productFilter, page-1, size);
      if (listProPaging.isEmpty()) {
         page=1;
         listProPaging = productService.getListByCateIdPaging(productFilter, 0, size);
      }
      List<Product> listProTotal = productService.getListByCateId(productFilter);
      int[] totalPages = new int[productService.getTotalPage(listProTotal, size)];

      model.addAttribute("cateId", cateId);
      model.addAttribute("catalogs", catalogs);
      model.addAttribute("listProTotal", listProTotal);
      model.addAttribute("listProPaging", listProPaging);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("totalPages", totalPages);
      return "client/menuPage";
   }

   @RequestMapping("/product")
   public String product(Model model,
                         @RequestParam(name = "id") long id){
      Product product = productService.findById(id);
      model.addAttribute("product", product);
      return "client/productPage";
   }
}
