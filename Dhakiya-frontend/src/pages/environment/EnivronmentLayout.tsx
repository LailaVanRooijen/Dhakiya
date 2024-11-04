export const EnivronmentLayout: React.FC<IEnivronmentLayoutProps> = ({
    notesCollection,
    flashcardsCollection,
    quizSets,
}) => {
    console.log("notes collection: ", notesCollection);
    console.log("flashcards collection ", flashcardsCollection);
    console.log("quiz sets ", quizSets);
    return (
        <div>
            {/* note collection  */}
            {notesCollection.notes == null ? (
                <div>There are no notes yet</div>
            ) : (
                <ul>
                    {notesCollection.notes.map((note) => (
                        <li key={note.id}>
                            <div>{note.content}</div>
                            <div>{note.label}</div>
                        </li>
                    ))}
                </ul>
            )}
            {/* flashcard collection */}
            {flashcardsCollection == null ? (
                <div>There are no flashcard sets yet</div>
            ) : (
                <ul>
                    {flashcardsCollection.map((collection) => (
                        <li key={collection.id}>{collection.title}</li>
                    ))}
                </ul>
            )}
        </div>
    );
};

interface IEnivronmentLayoutProps {
    notesCollection: {
        id: number;
        notes: INote[];
    };
    flashcardsCollection: [];
    quizSets: [];
}

interface INote {
    id: number;
    content: string;
    label: string;
}
