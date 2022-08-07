package moe.bitt.filebrowser.common.model.api

import java.util.*

data class FileModel(
    val path: String,
    val name: String,
    val size: Long,
    val extension: String,
    val modified: Date,
    val mode: Int,
    val isDir: Boolean,
    val isSymlink: Boolean,
    val type: String,
    val content: String
)
