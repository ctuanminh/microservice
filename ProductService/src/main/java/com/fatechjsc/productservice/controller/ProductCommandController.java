package com.fatechjsc.productservice.controller;

import com.fatechjsc.productservice.command.command.product.CreateProductCommand;
import com.fatechjsc.productservice.dto.ProductDto;
import com.fatechjsc.productservice.exceptions.InvalidParamException;
import com.fatechjsc.productservice.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/product")
public class ProductCommandController {
    private final CommandGateway commandGateway;

    @PostMapping("add")
    public String add(@Valid @RequestBody ProductDto model) throws Exception{
        CreateProductCommand command = new CreateProductCommand();
        BeanUtils.copyProperties(model, command);
        command.setProductId(UUID.randomUUID().toString());
        commandGateway.sendAndWait(command);
        return "Create Product success";
    }

    @PostMapping("update/{id}")
    public ResponseEntity<String> update(@Valid @PathVariable String id,
                                         @Valid @RequestBody ProductDto model,
                                          BindingResult result) throws Exception {
        if(result.hasErrors()){
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            throw new NotFoundException(errors.toString());
        }

        return ResponseEntity.ok("Update product success");
    }
}
