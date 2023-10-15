import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Materia(val id: Int?, var nombre: String) {
    val unidades = mutableListOf<Unidad>()
}

class Unidad(var calificacion: Int = 0)

fun guardarMateria(context: Context, materia: Materia) {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()
    val materiasString = sharedPreferences.getString("Materias", null)
    val materias: MutableList<Materia> = if (materiasString != null) {
        gson.fromJson(materiasString, object : TypeToken<MutableList<Materia>>() {}.type)
    } else {
        mutableListOf()
    }

    materias.add(materia)
    val materiasJson = gson.toJson(materias)
    editor.putString("Materias", materiasJson)
    editor.apply()
}

fun obtenerMaterias(context: Context): List<Materia> {
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val gson = Gson()
    val materiasString = sharedPreferences.getString("Materias", null)
    return if (materiasString != null) {
        gson.fromJson(materiasString, object : TypeToken<List<Materia>>() {}.type)
    } else {
        emptyList()
    }

}

fun actualizarMateria(context: Context, materia: Materia) {
    val materias = obtenerMaterias(context).toMutableList()
    val existingMateria = materias.find { it.id == materia.id }
    existingMateria?.nombre = materia.nombre
    val gson = Gson()
    val materiasJson = gson.toJson(materias)
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("Materias", materiasJson)
    editor.apply()
}

fun materiaExiste(context: Context, nombreMateria: String): Boolean {
    val materias = obtenerMaterias(context)
    return materias.any { it.nombre == nombreMateria }
}


fun eliminarMateria(context: Context, materiaId: Int) {
    val materias = obtenerMaterias(context).toMutableList()
    val materiaToRemove = materias.find { it.id == materiaId }
    materiaToRemove?.let { materias.remove(it) }
    val gson = Gson()
    val materiasJson = gson.toJson(materias)
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("Materias", materiasJson)
    editor.apply()
}

fun obtenerNombresMaterias(context: Context): List<String> {
    val materias = obtenerMaterias(context)
    return materias.map { it.nombre }
}

fun obtenerCalificacionesFilter(context: Context, name: String): List<Int> {
    var materianame = obtenerMaterias(context)[0]
    var calificacionesUnidades = materianame.unidades.map { it.calificacion }
    val materiasGuardadas = obtenerMaterias(context)

    if (materiasGuardadas.isNotEmpty()) {
        materianame = materiasGuardadas.first { it.nombre == name }


    }
    return materianame.unidades.map { it.calificacion }
}





fun eliminarTodasLasCalificaciones(context: Context) {
    val materias = obtenerMaterias(context).toMutableList()

    // Elimina todas las calificaciones en todas las unidades de todas las materias
    materias.forEach { materia ->
        materia.unidades.forEach { unidad ->
            unidad.calificacion = 0
        }
    }

    // Actualiza las materias con las calificaciones eliminadas
    val gson = Gson()
    val materiasJson = gson.toJson(materias)
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("Materias", materiasJson)
    editor.remove("Materias")
    editor.apply()
}


