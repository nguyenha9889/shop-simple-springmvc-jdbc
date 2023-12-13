package com.hng.controller;

import com.hng.dto.request.FormOrderDetail;
import com.hng.dto.request.ProductFilter;
import com.hng.model.Catalog;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;


@Controller
@RequestMapping({"/",""})
public class HomeController {

   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private IProductService productService;
   @RequestMapping
   public String home(Model model,
                      @RequestParam(name = "id", defaultValue = "0") long cateId,
                      @RequestParam(name = "sort", defaultValue = "") String sortByPrice,
                      @RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "size", defaultValue = "3") int size){

      List<Product> offerList = productService.getListFeatured().subList(0,2);
      model.addAttribute("offerList", offerList);

      ProductFilter productFilter = new ProductFilter();
      productFilter.setCategoryId(cateId); // Lọc theo cateId
      productFilter.setSortByPrice(sortByPrice); // Sắp xếp theo giá

      List<Product> listPerPage = null;
      if (cateId == 0) {
         listPerPage = productService.findAll().subList(0,4);
      }
      listPerPage = productService.getListByCateIdPaging(productFilter, page-1, size);

      // Menu Item - Lấy 4 category có sản phẩm
      List<Catalog> menuItem = catalogService.getListHaveProduct().subList(0,5);
      model.addAttribute("menuItem", menuItem);


      List<Product> listInMenuItem = productService.getListByCateId(productFilter);
      int[] totalPages = new int[productService.getTotalPage(listInMenuItem, size)];

      model.addAttribute("cateId", cateId);
      model.addAttribute("listPerPage", listPerPage);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("totalPages", totalPages);
      model.addAttribute("view", "menuHome");
      return "index";
   }

   @RequestMapping("/product")
   public String product(Model model,
                         @RequestParam(name = "id") long id) {

      Product product = productService.findById(id);
      FormOrderDetail formOrderDetail = new FormOrderDetail(
            product.getId(),
            product.getName(),
            product.getUnitPrice(),
            product.getImagePath(),
            product.getDescription()
      );
      model.addAttribute("formOrderDetail", formOrderDetail);
      return "index";
   }
}
