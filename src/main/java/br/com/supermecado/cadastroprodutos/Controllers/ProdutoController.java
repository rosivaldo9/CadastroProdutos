package br.com.supermecado.cadastroprodutos.Controllers;

import br.com.supermecado.cadastroprodutos.Model.Produto;
import br.com.supermecado.cadastroprodutos.Repositorios.ProdutoRepository;
import com.sun.org.apache.xalan.internal.xslt.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.GET)
    public String index() {
        return "/TelaCadastro";
    }

    @RequestMapping(value = "/cadastrarProduto", method = RequestMethod.POST)
    public String salvar(Produto produto) {
        produtoRepository.save(produto);
        return "redirect:/cadastrarProduto";
    }

    @GetMapping("/produtos")
    public ModelAndView listar() {
        ModelAndView produto = new ModelAndView("Lista");
        List<Produto> listaProduto = produtoRepository.findAll();
        produto.addObject("produtos", listaProduto);
        return produto;
    }

    @GetMapping(value = "/delete/{id}")
    public  String delete(@PathVariable("id") long id){
       Produto produto = produtoRepository.findAllById(id);
        produtoRepository.delete(produto);
        return "redirect:/produtos";
    }

    @GetMapping("/editarProduto/{id}")
    public String editarProduto(Model model, @PathVariable("id") long id){
    Produto produtoEdita = produtoRepository.findAllById(id);
    model.addAttribute("produto", produtoEdita);
    return "/editarProduto";
    }

    @RequestMapping(value = "/salvarEdicaoProduto", method = RequestMethod.POST)
    public String salvarProdutoEditado(Produto produto){
        produtoRepository.save(produto);
        return "redirect:/produtos";
    }

}
