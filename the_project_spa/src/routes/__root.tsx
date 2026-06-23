import { createRootRoute, Link, Outlet } from "@tanstack/react-router";
import { TanStackRouterDevtools } from "@tanstack/react-router-devtools";

const RootLayout = () => (
  <>
    <div className="p-2 flex gap-2">
      <Link to="/" className="[&.active]:font-bold">
        Home
      </Link>{" "}
      <Link to="/test" className="[&.active]:font-bold">
        Test
      </Link>
    </div>
    <hr />
    <Outlet />
    <TanStackRouterDevtools initialIsOpen={false} />
  </>
);

export const Route = createRootRoute({ component: RootLayout });
