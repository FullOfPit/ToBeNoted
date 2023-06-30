
type UserCredentials = {
    username: string,
    password: string,
    credentialsFound: "DEFAULT" | "FOUND" | "NOT_FOUND"
}
export type {UserCredentials};

const emptyCredentials: UserCredentials = {
    username: "",
    password: "",
    credentialsFound: "DEFAULT"
}
export {emptyCredentials};