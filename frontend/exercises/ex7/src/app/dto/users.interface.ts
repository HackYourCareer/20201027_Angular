export interface LoginDto {
  userName: string;
  password: string;
}

export interface UsernameValidationResult {
  result: boolean;
  message: string;
}
