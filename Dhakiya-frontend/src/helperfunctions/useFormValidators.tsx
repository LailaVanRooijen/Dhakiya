// hier methods die patch en post requests valideren.

import { PostEnvironmentRequest } from "../types/api";

export const ValidatePostEnvironment = (
  requestbody: PostEnvironmentRequest
) => {
  if (requestbody.title == "") {
    return false;
  }
  return true;
};
