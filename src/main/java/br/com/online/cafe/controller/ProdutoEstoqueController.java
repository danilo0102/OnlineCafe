package br.com.online.cafe.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.online.cafe.model.ProdutoEstoque;
import br.com.online.cafe.service.ProdutoEstoqueService;
import br.com.online.cafe.service.ProdutoService;

@Controller
public class ProdutoEstoqueController {

    @Autowired
    private ProdutoEstoqueService produtoEstoqueService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public ModelAndView findAll() {
	
//	Produto p = new Produto();
//	p.setCodigo(13456l);
//	p.setDescricao("Café em pó");
//	p.setUnidadeDeMedida(UnidadeDeMedida.UN);
//	produtoService.salvar(p);
//	
	
	ModelAndView mv = new ModelAndView("/lista_estoque");
	mv.addObject("produtosEstoque", produtoEstoqueService.buscarTodos());

	return mv;
	
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionar(ProdutoEstoque produtoEstoque) {

	ModelAndView mv = new ModelAndView("/adicionar_estoque");
	mv.addObject("produtoEstoque", produtoEstoque);
	mv.addObject("produtos", produtoService.buscarTodos());

	return mv;
	
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {

	return adicionar(produtoEstoqueService.buscarProdutoEstoquePorId(id));
	
    }

    @GetMapping("/apagar/{id}")
    public ModelAndView apagar(@PathVariable("id") Long cdProduto) {

	produtoEstoqueService.apagar(cdProduto);
	return findAll();
	
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid ProdutoEstoque produtoEstoque, BindingResult result, RedirectAttributes attributes) {
	
	if(produtoEstoque.getProduto() == null || produtoEstoque.getProduto().getCodigo() == null || produtoService.buscarPorCodigo( produtoEstoque.getProduto().getCodigo()) == null) {
	    result.addError(new ObjectError("produto", "Produto inválido."));
	}else {
	    produtoEstoque.setProduto(produtoService.buscarPorCodigo( produtoEstoque.getProduto().getCodigo()));
	}
	
	if (result.hasErrors()) {
	    return adicionar(produtoEstoque);
	}

	produtoEstoqueService.salvar(produtoEstoque);

	return findAll();
	
    }

}