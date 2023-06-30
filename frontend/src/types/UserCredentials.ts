enum credentialsFoundStatus {
    default = 0,
    found = 1,
    notFound = -1
}

type UserCredentials = {
    username: string,
    password: string,
    credentialsFound: credentialsFoundStatus,
}
export type {UserCredentials};

const emptyCredentials: UserCredentials = {
    username: "",
    password: "",
    credentialsFound: 0
}
export {emptyCredentials};