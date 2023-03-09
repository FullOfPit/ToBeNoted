type UserCredentials = {
    name: string,
    password: string,
}
export type {UserCredentials};

const emptyCredentials: UserCredentials = {
    name: "",
    password: "",
}
export {emptyCredentials};