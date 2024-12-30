export const getFormValues = (e: React.FormEvent) => {
  e.preventDefault();
  const formData = new FormData(e.currentTarget as HTMLFormElement);
  const formValues = Object.fromEntries(formData);
  return formValues;
};

/* Validators */
import {
  PatchNoteRequest,
  PostEnvironmentRequest,
  PostNoteRequest,
} from "../types/api";

export const validateEnvironmentRequestBody = (
  requestbody: PostEnvironmentRequest
) => {
  if (requestbody.title == "") {
    return false;
  }
  return true;
};

export const validateNoteRequestBody = (
  requestbody: PostNoteRequest | PatchNoteRequest,
  patch?: boolean
) => {
  if (patch) {
    if (
      requestbody.title === "" &&
      requestbody.content === "" &&
      requestbody.tagId
    ) {
      return false;
    }
  } else {
    if (requestbody.title == "" || requestbody.content == "") return false;
  }

  return true;
};
