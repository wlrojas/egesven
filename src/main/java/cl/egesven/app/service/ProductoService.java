package cl.egesven.app.service;

import cl.egesven.app.dto.ProductoDto;
import cl.egesven.app.entity.Producto;
import cl.egesven.app.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto registrarProducto(ProductoDto dto) {
        Producto p = Producto.builder()
        .id(dto.getId())
        .nombre(dto.getNombre())
        .descripcion(dto.getDescripcion())
        .precio(dto.getPrecio())
        .idCategoria(dto.getIdCategoria())
        .stock(dto.getStock())
        .urlImagen(dto.getUrlImagen())
        .build();
        return productoRepository.save(p);
    }

    public Producto modificarProducto(ProductoDto dto) {
        Producto p = productoRepository.findById(dto.getId()).orElseThrow();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setIdCategoria(dto.getIdCategoria());
        p.setStock(dto.getStock());
        p.setUrlImagen(dto.getUrlImagen());
        return productoRepository.save(p);
    }

    public Producto consultarProducto(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public ProductoDto toDto(Producto p) {
        ProductoDto dto = ProductoDto.builder()
        .id(p.getId())
        .nombre(p.getNombre())
        .descripcion(p.getDescripcion())
        .precio(p.getPrecio())
        .idCategoria(p.getIdCategoria())
        .stock(p.getStock())
        .urlImagen(p.getUrlImagen())
        .build();
        return dto;
    }

    public List<ProductoDto> toDto(List<Producto> productos) {
        return productos.stream().map(this::toDto).toList();
    }
}
