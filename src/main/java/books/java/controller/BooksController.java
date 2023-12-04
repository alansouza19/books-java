package books.java.controller;

import books.java.models.BooksModel;
import books.java.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksController {

    //Ponto de injeção para a interface BooksRepository para ter acesso a todos os métodos JPA
    // quando houver necessidade
    @Autowired
    BooksRepository booksRepository;

    //Aqui vamos implementar nosso CRUD


    @GetMapping("/busca-books")
    public List<BooksModel> listaBooks(){
        return booksRepository.findAll();
    }

    @GetMapping("/busca-books/{id}")
    public BooksModel bookById(@PathVariable(value = "id") long id){
        return booksRepository.findById(id);
    }
    @PostMapping("/salva-books")
    public BooksModel saveBooks(@RequestBody BooksModel books){
        return booksRepository.save(books);
    }

    @DeleteMapping("/deleta-books")
    public void deleteBooks(@RequestBody BooksModel booksModel){
        booksRepository.delete(booksModel);
    }


    @PutMapping("/atualiza-books")
    public BooksModel updateBooks(@RequestBody BooksModel booksModel ){
        return booksRepository.save(booksModel);
    }
}
