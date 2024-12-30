export const ENVIRONMENT_BASE_PATH = "/environments";

export const createEnvironmentPath = (environmentId: string | number) => {
  return `/environments/${environmentId}`;
};

interface NotePathProps {
  environmentId: string | number;
  noteCollectionId: string | number;
  noteId?: string | number;
}

export const createNotePath = ({
  environmentId,
  noteCollectionId,
  noteId,
}: NotePathProps): string => {
  if (noteId) {
    return `/environments/${environmentId}/note-collections/${noteCollectionId}/notes/${noteId}`;
  } else {
    return `/environments/${environmentId}/note-collections/${noteCollectionId}/notes`;
  }
};

interface NoteCollectionPathProps {
  environmentId: string | number;
  noteCollectionId: string | number;
}

export const createNoteCollectionPath = ({
  environmentId,
  noteCollectionId,
}: NoteCollectionPathProps) => {
  return `/environments/${environmentId}/note-collections/${noteCollectionId}`;
};

export const createTagPagePath = (environmentId: number | string) => {
  return `/environments/${environmentId}/tags`;
};
