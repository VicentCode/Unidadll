import android.content.Context

class Materia(val nombre: String) {
    val unidades = MutableList(4) { Unidad() }
}

class Unidad(var calificacion: Int = 0)

fun guardarMateria(context: Context, materia: String) {
    val sharedPreferences = context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putString(materia, materia)
    editor.commit()
}
fun guardarCalif (context: Context, materia: String, U1:String, U2:String, U3:String, U4:String){
    val sharedPreferences = context.getSharedPreferences("Calificaciones", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("$materia:u1", U1)
    editor.putString("$materia:u2", U2)
    editor.putString("$materia:u3", U3)
    editor.putString("$materia:u4", U4)
    editor.commit()
}

fun obtenerCalif(context: Context, materia: String, unidad: String): String? {
    val sharedPreferencesCalif = context.getSharedPreferences("Calificaciones", Context.MODE_PRIVATE)
    val CalificacionesString = sharedPreferencesCalif.getString("$materia:u$unidad", null)
    return CalificacionesString
}



fun obtenerMateriasALL(context: Context): List<Any> {
    val sharedPreferences = context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val valuesList = mutableListOf<Any>()
    val allKeys = sharedPreferences.all.keys
    for (key in allKeys) {
        val value = sharedPreferences.all[key]
        if (value != null) {
            valuesList.add(value)
        }
    }
    return valuesList
}

fun deleteMateria(context: Context, materia: String){
    val sharedPreferences = context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val sharedPreferencesCalif = context.getSharedPreferences("Calificaciones", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val editorCalif = sharedPreferencesCalif.edit()
    editor.remove(materia)
    editorCalif.remove("$materia:u1")
    editorCalif.remove("$materia:u2")
    editorCalif.remove("$materia:u3")
    editorCalif.remove("$materia:u4")
    editor.apply()
    editorCalif.apply()
}

fun deleteAll(context: Context){
    val sharedPreferences = context.getSharedPreferences("Materias", Context.MODE_PRIVATE)
    val sharedPreferencesCalif = context.getSharedPreferences("Calificaciones", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val editorCalif = sharedPreferencesCalif.edit()
    editor.clear()
    editorCalif.clear()
    editor.apply()
    editorCalif.apply()
}



/*
fun actualizarMateria(context: Context, materia: Materia) {
    val materias = obtenerMaterias(context).toMutableList()

    val existingMateria = materias.find { it.nombre == materia.nombre }
    existingMateria?.unidades?.clear()
    existingMateria?.unidades?.addAll(materia.unidades)

    // Actualiza la lista de materias en SharedPreferences
    val editor = context.getSharedPreferences("Materias", Context.MODE_PRIVATE).edit()
    val materiasStringNew = serializarMaterias(materias)
    editor.putString("Materias", materiasStringNew)
    editor.apply()
}

fun materiaExiste(context: Context, nombreMateria: String): Boolean {
    val materias = obtenerMaterias(context)
    return materias.any { it.nombre == nombreMateria }
}

fun eliminarMateria(context: Context, nombreMateria: String) {
    val materias = obtenerMaterias(context).toMutableList()
    val materiaToRemove = materias.find { it.nombre == nombreMateria }
    materiaToRemove?.let { materias.remove(it) }

    // Actualiza la lista de materias en SharedPreferences
    val editor = context.getSharedPreferences("Materias", Context.MODE_PRIVATE).edit()
    val materiasStringNew = serializarMaterias(materias)
    editor.putString("Materias", materiasStringNew)
    editor.apply()
}

fun obtenerNombresMaterias(context: Context): List<String> {
    val materias = obtenerMaterias(context)
    return materias.map { it.nombre }
}

fun obtenerCalificaciones(context: Context, nombreMateria: String): List<Int> {
    val materias = obtenerMaterias(context)
    val materia = materias.find { it.nombre == nombreMateria }
    return materia?.unidades?.map { it.calificacion } ?: emptyList()
}

fun eliminarTodasLasCalificaciones(context: Context) {
    val materias = obtenerMaterias(context).toMutableList()
    materias.forEach { materia ->
        materia.unidades.forEach { unidad ->
            unidad.calificacion = 0
        }
    }

    // Actualiza la lista de materias en SharedPreferences
    val editor = context.getSharedPreferences("Materias", Context.MODE_PRIVATE).edit()
    val materiasStringNew = serializarMaterias(materias)
    editor.putString("Materias", materiasStringNew)
    editor.apply()
}

private fun serializarMaterias(materias: List<Materia>): String {
    return materias.joinToString(";") { materia ->
        val unidadesString = materia.unidades.joinToString(",") { it.calificacion.toString() }
        "${materia.nombre}#$unidadesString"
    }
}

private fun deserializarMaterias(materiasString: String): MutableList<Materia> {
    val materiaStrings = materiasString.split(";")
    val materias = mutableListOf<Materia>()
    for (materiaString in materiaStrings) {
        val parts = materiaString.split("#")
        if (parts.size == 2) {
            val nombre = parts[0]
            val unidadesString = parts[1].split(",")
            val unidades = unidadesString.map { Unidad(it.toInt()) }
            materias.add(Materia(nombre).apply { this.unidades.addAll(unidades) })
        }
    }
    return materias
}*/
