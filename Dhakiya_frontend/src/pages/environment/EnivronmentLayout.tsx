export const EnivronmentLayout: React.FC<EnivronmentLayoutProps> = ({
    notes,
    flashcards,
    tests,
}) => {
    return (
        <div>
            <div>{notes}</div>
            <div>{flashcards}</div>
            <div>{tests}</div>
        </div>
    );
};

interface EnivronmentLayoutProps {
    notes: React.ReactNode;
    flashcards: React.ReactNode;
    tests: React.ReactNode;
}
