package ucom.py.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import ucom.py.entities.Presupuesto;

@ApplicationScoped
public class PresupuestoRepository {

    // Declaro la ruta del archivo donde se guardarán los datos de los presupuestos.
    private static final String FILE_PATH = "src/main/resources/data/presupuesto.json";

    // Defino la lista que contendrá los presupuestos y un objeto para manejar JSON.
    private List<Presupuesto> presupuestoList;
    private ObjectMapper objectMapper;

    // En el constructor, inicializo el ObjectMapper y cargo los datos desde el archivo.
    public PresupuestoRepository() {
        objectMapper = new ObjectMapper();
        presupuestoList = cargarDatos();
    }

    // Método para cargar los datos de los presupuestos desde el archivo.
    public List<Presupuesto> cargarDatos() {
        try {
            File file = new File(FILE_PATH);
            // Si el archivo existe, lo leo y convierto su contenido en una lista de presupuestos.
            if (file.exists()) {
                System.out.println("Datos cargados: " + presupuestoList);
                return objectMapper.readValue(file, new TypeReference<List<Presupuesto>>() {});
            } else {
                // Si el archivo no existe, retorno una lista vacía.
                return new ArrayList<>();
            }
        } catch (IOException e) {
            // Si ocurre un error al leer el archivo, imprimo el error y retorno una lista vacía.
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Método para guardar los datos de la lista de presupuestos en el archivo.
    public void guardarDatos() {
        try {
            // Escribo la lista de presupuestos en el archivo como JSON.
            objectMapper.writeValue(new File(FILE_PATH), presupuestoList);
        } catch (IOException e) {
            // Si ocurre un error al guardar el archivo, imprimo el error.
            e.printStackTrace();
        }
    }

    // Método para obtener un presupuesto por su ID.
    public Presupuesto obtenerById(Integer id) {
        // Busco en la lista el presupuesto que coincida con el ID proporcionado.
        return presupuestoList.stream()
            .filter(presupuesto -> presupuesto.getId() == id) 
            .findFirst()
            .orElse(null);
    }

    // Método para listar todos los presupuestos.
    public List<Presupuesto> listar() {
        // Retorno una copia de la lista de presupuestos para no modificar la original.
        return new ArrayList<>(presupuestoList);
    }

    // Método para agregar un nuevo presupuesto a la lista.
    public Presupuesto agregarPresupuesto(Presupuesto param) {
        // Genero un nuevo ID para el presupuesto basado en el ID más alto existente.
        Integer newId = presupuestoList.isEmpty() ? 1
                : presupuestoList.stream()
                        .mapToInt(Presupuesto::getId)
                        .max()
                        .getAsInt() + 1;

        // Asigno el nuevo ID al presupuesto y lo añado a la lista.
        param.setId(newId);
        presupuestoList.add(param);
        // Guardo la lista actualizada en el archivo.
        guardarDatos();
        return param;
    }

    // Método para eliminar un presupuesto por su ID.
    public void eliminar(Integer id) {
        // Filtro la lista para excluir el presupuesto con el ID especificado.
        presupuestoList = presupuestoList.stream()
                .filter(presupuesto -> !Integer.valueOf(presupuesto.getId()).equals(id)) 
                .collect(Collectors.toList());
        // Guardo la lista actualizada en el archivo.
        guardarDatos();
    }
}
