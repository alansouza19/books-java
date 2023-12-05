package books.java.controller;

import books.java.dto.BooksDTO;
import books.java.models.BooksModel;
import books.java.repository.BooksRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Indica que a classe é um controlador Spring responsável
// por lidar com solicitações HTTP e retornar respostas.

@RequestMapping("/api") //Especifica o caminho base para todas as rotas definidas nesta classe.
// Portanto, todas as rotas começarão com "/api".

public class BooksController {


    @Autowired//Ponto de injeção para a interface BooksRepository para ter acesso a todos
    // os métodos JPA quando houver necessidade.
    BooksRepository booksRepository;

    //Aqui vamos implementar nosso CRUD
    @GetMapping("/busca-books")
    public List<BooksModel> listaBooks(){
        return booksRepository.findAll();
    }

    @GetMapping("/busca-books/{id}")
    public BooksModel bookById(@PathVariable(value = "id") long id){
        BooksModel existingBookModel = booksRepository.findById(id);
        if(existingBookModel != null){
            return booksRepository.findById(id);
        }
        else {
            throw new RuntimeException("Livro não encontrado");
        }

    }
    @PostMapping("/salva-books")
    public BooksModel saveBooks(@RequestBody BooksDTO booksDTO){
        BooksModel booksModel = convertDTOToModel(booksDTO); // Converte BooksDTO para BooksModel
        return booksRepository.save(booksModel);
    }

    @DeleteMapping("/deleta-books")
    public void deleteBooks(@RequestBody BooksModel booksModel){
        booksRepository.delete(booksModel);
    }


    @PutMapping("/atualiza-books/{id}")
    public BooksModel updateBooks(@PathVariable(value = "id") long id, @RequestBody BooksDTO booksDTO){
        BooksModel existingBookModel = booksRepository.findById(id);
        if(existingBookModel != null){
            updateModelFromDTO(existingBookModel, booksDTO);
            return booksRepository.save(existingBookModel);
        }
        else {
            throw new RuntimeException("Livro não encontrado");
        }
    }

    //Auxilia converter DTO para Model
    private BooksModel convertDTOToModel(BooksDTO booksDTO){
        BooksModel booksModel = new BooksModel();
        BeanUtils.copyProperties(booksDTO, booksModel);
        return booksModel;
    }


    //Auxilia para atualizar Model a partir de DTO
    private void updateModelFromDTO(BooksModel booksModel, BooksDTO booksDTO){
        BeanUtils.copyProperties(booksDTO, booksModel);
    }
}
