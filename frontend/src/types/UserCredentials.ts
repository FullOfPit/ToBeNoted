type UserCredentials = {
    username: string,
    password: string,
}
export type {UserCredentials};

const emptyCredentials: UserCredentials = {
    username: " ",
    password: " ",
}
export {emptyCredentials};