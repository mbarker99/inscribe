package com.embarkapps.inscribe.notes.presentation.noteslist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.embarkapps.inscribe.notes.domain.model.Note
import com.example.compose.InscribeTheme

@Composable
fun NoteCard(
    note: Note,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = { onClick() }
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = note.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 4,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Text(
                text = note.content,
                fontSize = 12.sp,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

@PreviewLightDark
@Composable
fun NoteCardPreview(modifier: Modifier = Modifier) {
    InscribeTheme {
        NoteCard(
            note = previewNote,
            onClick = {},
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background
            )
        )
    }
}

internal val previewNote = Note(
    title = "Hello notes!",
    content = "This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!This is a note! It says things that a note would say!",
    id = 1
)