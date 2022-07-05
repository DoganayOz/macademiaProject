package com.employee.employeeProject.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.employeeProject.model.DrawPrize;
import com.employee.employeeProject.model.Employee;
import com.employee.employeeProject.service.DrawPrizeService;

@RestController
@RequestMapping("/DrawPrize")
public class DrawPrizeController {

	@Autowired
	private DrawPrizeService drawPrizeService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DrawPrize addDrawPrize(@RequestBody DrawPrize drawPrize) {
	    return drawPrizeService.addDrawPrize(drawPrize);
	}

	@GetMapping
	public List<DrawPrize> getAllDrawPrize(){
	    return drawPrizeService.getAllDrawPrize();
	}
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDrawPrize(@PathVariable int id){
      try {
    	  drawPrizeService.deleteDrawPrizeById(id);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(RuntimeException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
	
    
    @PutMapping("/updateDrawPrize")
    public ResponseEntity<String> updateDrawPrize(@RequestBody DrawPrize drawPrize) {  
      try {
    	  drawPrizeService.updateDrawPrize(drawPrize);
        return new ResponseEntity<String>(HttpStatus.OK);
      }catch(NoSuchElementException ex){
        System.out.println(ex.getMessage());
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
      }
    }
    

    @PostMapping("/getWinnerEmployeeByMonth/{monthByYear}")
    public Employee getWinnerEmployeeByMonth(@PathVariable String monthByYear) {
    	return drawPrizeService.getWinnerEmployeeByMonth(monthByYear);
    }
    
}
