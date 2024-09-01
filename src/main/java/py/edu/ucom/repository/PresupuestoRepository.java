package py.edu.ucom.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import py.edu.ucom.entities.apiresponse.Presupuesto;
import py.edu.ucom.entities.apiresponse.Gastos;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PresupuestoRepository {
    private List<Presupuesto> presupuestosList; // Lista de presupuestos
    private final String filePath = "src/main/resources/presupuestos.json"; // Ruta del archivo JSON

    public PresupuestoRepository() {
        presupuestosList = new ArrayList<>();
        cargarDatos(); // Cargar los datos al inicializar
    }

    // Cargar los datos desde el archivo JSON
    public void cargarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                presupuestosList = objectMapper.readValue(file, new TypeReference<List<Presupuesto>>() {
                });
            } else {
                presupuestosList = new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Guardar los datos en el archivo JSON
    public void guardarDatos() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), presupuestosList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Obtener un presupuesto por su ID
    public Presupuesto obtenerById(Integer id) {
        return presupuestosList.stream()
                .filter(presupuesto -> presupuesto.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Listar todos los presupuestos
    public List<Presupuesto> listar() {
        return new ArrayList<>(presupuestosList);
    }

    // Agregar un nuevo gasto a un presupuesto específico
    public Presupuesto agregarGasto(Integer presupuestoId, Gastos gasto) {
        Optional<Presupuesto> presupuestoOpt = presupuestosList.stream()
                .filter(p -> p.getId() == presupuestoId)
                .findFirst();

        if (presupuestoOpt.isPresent()) {
            Presupuesto presupuesto = presupuestoOpt.get();
            presupuesto.getGastos().add(gasto);
            guardarDatos(); // Guardar cambios en el archivo JSON
            return presupuesto;
        } else {
            return null;
        }
    }

    // Modificar un presupuesto existente
    public Presupuesto modificar(Presupuesto presupuestoModificado) {
        Optional<Presupuesto> existingPresupuesto = presupuestosList.stream()
                .filter(p -> p.getId() == presupuestoModificado.getId())
                .findFirst();

        if (existingPresupuesto.isPresent()) {
            presupuestosList = presupuestosList.stream()
                    .map(p -> p.getId() == presupuestoModificado.getId() ? presupuestoModificado : p)
                    .collect(Collectors.toList());
            guardarDatos(); // Guardar cambios en el archivo JSON
            return presupuestoModificado;
        } else {
            return null;
        }
    }

    // Eliminar un presupuesto por su ID
    public void eliminar(Integer id) {
        presupuestosList = presupuestosList.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
        guardarDatos(); // Guardar cambios en el archivo JSON
    }
}