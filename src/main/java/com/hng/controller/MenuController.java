package com.hng.controller;

import com.hng.dto.request.ProductFilter;
import com.hng.model.Cart;
import com.hng.model.Catalog;
import com.hng.model.Product;
import com.hng.service.ICatalogService;
import com.hng.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/menu")
public class MenuController {
   @Autowired
   private ICatalogService catalogService;
   @Autowired
   private IProductService productService;

   @RequestMapping
   public String menu(Model model,
                      HttpSession session,
                      @RequestParam(name = "id", defaultValue = "0") long cateId,
                      @RequestParam(name = "sort", defaultValue = "") String sortByPrice,
                      @RequestParam(name = "page", defaultValue = "1") int page,
                      @RequestParam(name = "size", defaultValue = "5") int size){

      Cart cart = (Cart) session.getAttribute("cart");
      model.addAttribute("cart", cart);

      // Chỉ lấy category có sản phẩm
      List<Catalog> catalogs = catalogService.getListHaveProduct();
      model.addAttribute("menuList", catalogs);

      ProductFilter productFilter = new ProductFilter();
      if (cateId == 0) {
         cateId = catalogs.get(0).getId();
      }
      productFilter.setCategoryId(cateId); // Lọc theo cateId
      productFilter.setSortByPrice(sortByPrice); // Sắp xếp theo giá

      List<Product> menuItem = productService.getListByCateIdPaging(productFilter, page-1, size);

      if (menuItem.isEmpty()) {
         page=1;
         menuItem = productService.getListByCateIdPaging(productFilter, 0, size);
      }

      List<Product> listProTotal = productService.getListByCateId(productFilter);
      int[] totalPages = new int[productService.getTotalPage(listProTotal, size)];

      model.addAttribute("cateId", cateId);
      model.addAttribute("menuItem", menuItem);
      model.addAttribute("currentPage", page);
      model.addAttribute("size", size);
      model.addAttribute("totalPages", totalPages);
      return "client/menu";
   }
}
